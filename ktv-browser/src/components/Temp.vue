<template>
  <!-- <div style="height:10em"> -->
    <el-upload
      class="upload-demo"
      action="/user/file.do"
      :show-file-list="showFileList"
      :before-upload="beforeUpload"
      :on-success="onSuccess"

      multiple>
      <el-button size="small" type="primary">上传歌曲</el-button>
      <div slot="tip" class="el-upload__tip"></div>
    </el-upload>
  <!-- </div> -->
</template>

<script>
  export default {
    data() {
      return {
        showFileList : false,
        showUploadButton: false
        // fileList: [{
        //   name: 'food.jpeg',
        //   url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        // }, {
        //   name: 'food2.jpeg',
        //   url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        // }]
      };
    },
    methods: {
      beforeUpload(file, fileList) {
        if (file.name.endsWith(".mp3"))
          return true;
        this.$message.error('"' + file.name + '" 不是 mp3 文件')
        return false;
      },
      onSuccess(file, fileList) {
        this.$message.success('上传成功')
      }
    }
  }
</script>