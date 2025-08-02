<template>
    <div class="file_lab1_container" id="app">
        <h1>file_upload_1</h1>
        <div class="file1_containwe">
            <!-- 1. 阻止默认提交，绑定自定义提交方法 -->
            <form @submit.prevent="handleUpload">
                <!-- 2. 添加ref，用于获取文件 -->
                <input type="file" name="file" ref="fileInput">
                <br>
                <input type="submit" class="upload_submit" value="upload file" name="submit">
            </form>
        </div>

        <!-- 添加默认提示文本，确保路径为空时也有显示 -->
        <span class="hint">{{ filePath }}</span>
    </div>
</template>

<script>
export default {
    name: 'FileUploadOnePage',
    data() {
        return {
            filePath: '' // 初始为空字符串
        };
    },
    methods: {
        handleUpload() {
            const file = this.$refs.fileInput.files[0];
            if (!file) {
                alert("请选择文件");
                return;
            }

            const formData = new FormData();
            formData.append('file', file); 


            // 修复：禁用 axios 默认数据转换，确保 FormData 原样发送
            this.$axios.post('http://127.0.0.1:9876/upload', formData, {
                transformRequest: []
            })
            .then(response => {

                alert(response.data.msg); 
                this.filePath = response.data.path;
            })
            .catch(error => {
                // 补充：打印错误响应详情
                console.error("请求错误:", error);
                console.error("错误响应数据:", error.response?.data);
                alert("上传失败：" + error.message);
            });
        }
    }
}
</script>


<style scoped>

h1 {
    text-align: center;
}

/* 添加：使 .file1_containwe 水平居中 */
.file_lab1_container {
    width: 100%; /* 确保父容器宽度充满可用空间 */
}

.file1_containwe {
    margin: 50px 650px 0; /* 水平居中（auto），顶部间距20px */
    max-width: 500px; /* 可选：限制最大宽度，避免过宽 */
    padding: 0 20px; /* 可
    选：添加左右内边距，适配小屏幕 */
}

.upload_submit {
    margin-top: 20px;
}

.hint {
    display: block;
    text-align: center;
    margin-top: 50px;
}

</style>