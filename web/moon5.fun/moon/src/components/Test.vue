<template>
  <div id="app">
    <!-- 添加内容容器，统一包裹搜索框和表格 -->
    <div class="content-container">
      <!-- 搜索区域添加类名，便于样式控制 -->
      <div class="search-container">
        <input 
          type="text" 
          v-model="searchTerm" 
          placeholder="输入搜索关键词" 
          id="searchInput"
        >
        <button @click="handleSearch" style="margin-left: 8px; padding: 10px 16px;">搜索</button>
      </div>

      <!-- 数据表格：添加 v-for 循环渲染数据 -->
      <table class="test_table">
        <thead>
          <tr>
            <th>ID</th>
            <th>name</th>
          </tr>
        </thead>
        <tbody>
          <!-- 使用 v-for 循环 deptList 渲染表格行 -->
          <tr v-for="item in deptList" :key="item.id">  <!-- 添加此行 -->
            <td>{{ item.id }}</td>                      <!-- 显示 ID -->
            <td>{{ item.name }}</td>                    <!-- 显示 name -->
          </tr>                                         <!-- 添加此行 -->
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
        /* ... 现有样式 ... */

        /* 内容容器：垂直排列并居中所有子元素 */
        .content-container {
        display: flex;
        flex-direction: column; /* 垂直排列搜索框和表格 */
        align-items: center; /* 水平居中 */
        width: 100%;
        }

        /* 搜索区域：居中内部元素并添加间距 */
        .search-container {
        display: flex;
        align-items: center; /* 搜索框和按钮垂直居中对齐 */
        gap: 8px; /* 搜索框和按钮之间的间距 */
        margin-bottom: 20px; /* 与表格保持距离 */
        }

        /* 搜索框宽度保持不变，通过父容器居中 */
        input[type="text"] {
        width: 300px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        /* 移除 justify-content: center（属于父容器属性） */
        }

        table {
        width: 80%;
        margin: 30px auto; /* 让表格居中 */
        border-collapse: collapse;
        border: 2px solid #ddd; /* 整体边框颜色 */
        }
        th,
        td {
            line-height: 40px; /* 行高 */
            border: 1px solid #ddd; /* 每行边框 */
            text-align: center;
            font-size: 16px;
        }
        th {
            background-color: #007bff; /* 表头背景色 */
            color: #ffffff;
        }
        /* 这次奇偶行用 class 来写 */
        tr.even {
            background-color: #eee; /* 偶数行浅灰色 */
        }
        tr:hover {
            background-color: #ccc; /* 鼠标悬停时的背景色:蓝色 */
        }
        /* 把90分以上的要特殊标识绿色 */
        td.perfect {
            background-color: #00ff00; /* 绿色背景 */
        }

</style>

<script>
export default {
  name: 'TestPage',
  data() {
    return {
      searchTerm: '', // 绑定搜索框输入
      deptList: [] // 存储查询结果
    }
  },
  methods: {
    handleSearch() {
      // 发送 GET 请求到后端接口，传递 id 参数
      this.$axios.get('http://127.0.0.1:9876/test', {
        params: { 
          id: this.searchTerm // 将输入框内容作为 id 参数
        }
      })
      .then(response => {
        // 假设后端返回格式为 { data: [...] }
        this.deptList = response.data.data || [];
      })
      .catch(error => {
        console.error('查询失败:', error);
        alert('查询失败，请检查接口或网络');
      });
    }
  }
}
</script>

