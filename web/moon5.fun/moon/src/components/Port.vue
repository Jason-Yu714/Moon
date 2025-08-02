<template>
  <div id="app">

    <h1 class="port-heard">PORT</h1>

    <!-- 添加内容容器，统一包裹搜索框和表格 -->
    <div class="content-container">
      <!-- 搜索区域添加类名，便于样式控制 -->
      <div class="search-container">
        <input 
          type="text" 
          v-model="searchTerm" 
          placeholder="输入搜索关键词" 
          id="searchInput"
          class="searchInput"
        >
        <button @click="handleSearch" style="margin-left: 8px; padding: 10px 16px;">search</button>
        <!-- 添加"添加"按钮 -->
        <button @click="showAddForm = true" style="margin-left: 8px; padding: 10px 16px;">add</button>
      </div>

      <!-- 数据表格：添加点击事件 -->
      <table class="test_table">
        <thead>
          <tr>
            <th>Port</th>
            <th>serverName</th>
            <th>message</th>
            <th>attackMethod</th>
          </tr>
        </thead>
        <tbody>
          <!-- 添加@click事件，传递当前行数据 -->
          <tr v-for="item in deptList" :key="item.id" @click="showDetail(item)">  
            <td>{{ item.port }}</td>                     
            <td>{{ item.serverName }}</td>   
            <td>{{ item.message }}</td>      
            <td>{{ item.attackMethod }}</td>           
          </tr>                                         
        </tbody>
      </table>

      <!-- 添加端口表单弹窗保持不变 -->
      <div v-if="showAddForm" class="modal-overlay">
        <div class="modal-container">
          <div class="modal-header">
            <h3>添加端口信息</h3>
            <button @click="showAddForm = false" class="close-btn">&times;</button>
          </div>
          <div class="form-group">
            <input type="text" v-model="newPort" placeholder="port" required>
            <input type="text" v-model="newServerName" placeholder="serverNmae" required>
            <input type="text" v-model="newMessage" placeholder="message" required>
            <input type="text" v-model="newAttackMethod" placeholder="attackMethod" required>
          </div>
          <div class="modal-footer">
            <button @click="showAddForm = false" class="cancel-btn">取消</button>
            <button @click="handleAddPort" class="confirm-btn">添加</button>
          </div>
        </div>
      </div>

      <!-- 新增详情弹窗 -->
      <div v-if="showDetailModal" class="detail-modal-overlay">
        <div class="detail-modal-container">
          <div class="modal-header">
            <h3>端口详情</h3>
            <button @click="showDetailModal = false" class="close-btn">&times;</button>
          </div>
          <div class="detail-content">
            <p><strong>Port:</strong> {{ currentItem.port }}</p>
            <p><strong>Server Name:</strong> {{ currentItem.serverName }}</p>
            <p><strong>Message:</strong> {{ currentItem.message }}</p>
            <p><strong>Attack Method:</strong> {{ currentItem.attackMethod }}</p>
          </div>
          <div class="modal-footer">
            <button @click="showDetailModal = false" class="confirm-btn">关闭</button>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>



<script>
export default {
  name: 'PortPage',
  data() {
    return {
      searchTerm: '', 
      deptList: [], 
      newPort: '',
      newServerName: '',
      newMessage: '',
      newAttackMethod: '',
      showAddForm: false,
      // 新增：详情弹窗控制
      showDetailModal: false,  // 控制弹窗显示
      currentItem: null        // 存储当前点击行数据
    }
  },
  // 添加页面加载时自动查询（默认显示所有信息）
  mounted() {
    this.handleSearch();
  },
  methods: {
    handleSearch() {
      this.$axios.get('http://127.0.0.1:9876/port', {
        // 关键优化：仅在有搜索内容时传递参数
        params: this.searchTerm ? { p: this.searchTerm } : {}
      })
      .then(response => {
        this.deptList = response.data.data || [];
      })
      .catch(error => {
        console.error('查询失败:', error);
        alert('查询失败，请检查接口或网络');
      });
    },

    
    // 添加端口信息方法 - 修复成功提示不显示问题
    handleAddPort() {
      // 1. 增强输入验证
      if (!this.newPort || !this.newServerName || !this.newMessage || !this.newAttackMethod) {
        alert('请填写所有必填字段');
        return;
      }
      
      // 2. 准备请求数据，确保数据类型正确
      const postData = {
        port: this.newPort.trim(),       // 去除空格
        serverName: this.newServerName.trim(),
        message: this.newMessage.trim(),
        attackMethod: this.newAttackMethod.trim()
      };
      
      console.log('发送添加请求:', postData); // 添加调试日志
      
      // 3. 发送POST请求到addPort接口
      this.$axios.post('http://127.0.0.1:9876/addPort', postData, {
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        console.log('添加接口响应:', response.data); // 调试日志
        
        // 修复：调整成功条件判断，适配后端实际响应格式
        // 可能的情况：后端返回 { success: true } 或 { code: 200 } 等
        const isSuccess = response.data.code === 1 ||  // 原判断条件
                          response.data.success === true ||  // 新增：成功标志
                          response.status === 200;  // 新增：HTTP成功状态码
        
        if (isSuccess) {
          alert('添加成功！');
          // 清空表单并关闭弹窗
          this.newPort = '';
          this.newServerName = '';
          this.newMessage = '';
          this.newAttackMethod = '';
          this.showAddForm = false;
          // 可选：添加成功后刷新列表
          // this.handleSearch();
        } else {
          alert('添加失败：' + (response.data.msg || '未知错误'));
        }
      })
      .catch(error => {
        // 4. 增强错误信息显示
        console.error('添加请求失败详情:', error);
        let errorMsg = '添加失败: ';
        
        if (error.response) {
          // 服务器返回了响应
          errorMsg += `服务器错误 ${error.response.status} - ${error.response.data?.msg || '请求参数错误'}`;
        } else if (error.request) {
          // 请求已发送但无响应
          errorMsg += '未收到服务器响应，请检查后端服务';
        } else {
          // 请求配置错误
          errorMsg += '请求配置错误: ' + error.message;
        }
        
        alert(errorMsg);
      });
    },

    // 新增：显示详情弹窗方法
    showDetail(item) {
      this.currentItem = item;       // 存储点击行数据
      this.showDetailModal = true;   // 显示弹窗
    }
  }
}
</script>


<style scoped>
  @import url(../assets/css/Port.css);

  /* 修改：将背景样式应用于页面根容器，替代全局 html 选择器 */
  #app {
    background-color: #f5f5f5; /* 设置目标背景色（示例：浅灰色） */
    min-height: 100vh; /* 确保背景覆盖整个视口高度 */
    margin: 0; /* 移除默认边距 */
    padding: 0; /* 移除默认内边距 */
  }
</style>

