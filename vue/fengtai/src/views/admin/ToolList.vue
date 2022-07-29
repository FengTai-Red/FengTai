<template>
  <div>
    <!-- 列表 -->
    <el-button type="primary" style="margin-left: 20px;" @click="add()">新增</el-button>
    <el-table :data="toolData" style="width: 100%">
      <el-table-column label="文件名" width="180">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="简介" width="450">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件" width="450">
        <template #default="scope">
          <span style="margin-left: 10px">{{ scope.row.path }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handEdit(scope.row.id)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)" >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 翻页 -->
    <el-pagination background layout="prev, pager, next" :page-count="Math.floor(totalElements/10)+1" @current-change="page"/>
    <!-- 编辑窗 -->
    <div>
      <el-dialog title="编辑" v-model="upDialogVisible" width="50%" >
        <el-form ref="updateForm" :model="updateTool" :rules="rules" label-width="80px">
          <el-form-item label="文件名" prop="name">
            <el-input v-model="updateTool.name"  maxlength="7"></el-input>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input v-model="updateTool.description"  maxlength="23"></el-input>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="upDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleUpload(updateTool)">确 定</el-button>
        </span>
        </template>
      </el-dialog>
    </div>
    <!-- 新增窗 -->
    <div>
      <el-dialog title="新增" v-model="addDialogVisible" width="50%" >
        <el-form ref="addForm" :model="addTool" :rules="rules" label-width="80px">
          <el-form-item label="文件名" prop="name">
            <el-input v-model="addTool.name" maxlength="7"></el-input>
          </el-form-item>
          <el-form-item label="简介" prop="description">
            <el-input v-model="addTool.description" maxlength="23"></el-input>
          </el-form-item>
          <el-form-item label="文件">
            <span style="width: 300px;">
              <el-upload class="upload-demo" 
                ref="upload" 
                action="http://localhost:8181/admin/tool/upload" 
                name="file"
                :auto-upload="false" 
                :limit="1" 
                :on-success="handleFileUploadSuccess"
                :on-remove="handleRemove" 
                :on-change="handleChange"
                :before-upload="handleBeforeUpload"
                >
                <template #trigger>
                  <el-button type="primary">选择文件</el-button>
                </template>
              </el-upload>
            </span>
          </el-form-item>
          <el-form-item label="文件路径" v-show="false">
            <el-input v-model="addTool.path" maxlength="7"></el-input>
          </el-form-item>
        </el-form>
        <template v-slot:footer>
        <span class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="handleAdd(addTool)">确 定</el-button>
        </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import axios from "axios"
  var controllerPath = ''
  export default {
    name: "Upload",
    created(){
      controllerPath = this.$config.controllerPath
      const _this = this
      axios.get(controllerPath + '/tool/page/1/10').then(function (resp){
        _this.toolData = resp.data.content
        _this.totalElements = resp.data.totalElements
      })
    },
    data() {
      return {
        fileSizeMax: false,
        totalElements: null,
        size: null,
        toolData: null,
        visible: null,
        upDialogVisible: false,  // 修改窗
        addDialogVisible: false,  // 新增窗
        isFileEmpty: true,  // 文件是否上传
        updateTool: {
          name: '',
          description: '',
          path: '',
        },
        addTool: {
          name: '',
          description: '',
          path: '',
        },
        rules: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
        },
      }
    },
    methods: {
      page(currentPage){
        const _this = this
        axios.get(controllerPath + '/tool/page/'+ currentPage+ '/10').then(function (resp){
          _this.toolData = resp.data.content
          _this.totalElements = resp.data.totalElements
          _this.size = resp.data.size
        })
      },
      /**
       * 编辑
       */
			handEdit(id) {
        const _this = this
				this.upDialogVisible = true;
        axios.get(controllerPath + '/tool/' + id).then(function (resp){
          _this.updateTool = resp.data
        })
			},
      /**
       * 删除
       */
      handleDelete(id){
        this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete(controllerPath + '/admin/tool/deleteTool/' + id)
          window.location.reload();  // 刷新窗口
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      /**
       * 编辑框-确定
       */
			handleUpload (updateTool) {
        this.$refs.updateForm.validate((valid) => {
          if (valid && fileSize) {
            const _this = this
            this.upDialogVisible = false
            axios.put(controllerPath + '/admin/tool/updateTool', updateTool).then(function(resp) {
              if (resp.data == 'success'){
                console.log('成功');
              }else{
                alert('修改失败')
              }
            })
            window.location.reload();  // 刷新窗口
          } else {
            console.log("校验失败");
          }
        })
			},
      /**
       * 新增
       */
			add() {
        const _this = this
				this.addDialogVisible = true;
			},
      /**
       * 上传框-确定
       * https://blog.51cto.com/codinggorit/3753580
       */
      handleAdd(addTool){
        console.log("=======确定 handleAdd=======")
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            if (!this.isFileEmpty) {
              this.submitUpload()
                if (this.fileSizeMax) {
                  const _this = this
                  this.addDialogVisible = false
                  axios.post(controllerPath + '/admin/tool/save', addTool).then(function(resp) {
                    if (resp.data == 'success'){
                      console.log('成功');
                      // window.location.reload();  // 刷新窗口
                    }else{
                      alert('新增失败')
                    }
                  })
                } else {
                  alert('上传文件大小不能超过 99MB')
                }
            } else {
              console.log("未选择文件");
              this.$message({
                type: 'warning',
                message: '请选择文件'
              });
            }
          } else {
            console.log("校验失败");
          }
        })
      },
      // 值改变 判读是否有值
      handleChange(file) {
        console.log("=======文件状态改变 handleChange=======");
        console.log(file);
        if (file != "undefined") {
          this.isFileEmpty = false;
        } else {
          this.isFileEmpty = true;
          console.log("文件有误");
        }
      },
      // 删除文件
      handleRemove(file) {
        console.log("=======删除文件 handleRemove=======")
        this.isFileEmpty = true;
        console.log(file);
      },
      // 上传前
      handleBeforeUpload(file) {
        console.log("=======上传文件前 handleBeforeUpload=======");
        const isLt10M = file.size / 1024 / 1024 < 99
        if (!isLt10M) {
          this.fileSizeMax = false
          this.$message.error('上传文件大小不能超过 99MB!')
          return false;
        }else{
          this.fileSizeMax = true
        }
      },
      // 上传文件到服务器
      submitUpload() {
        console.log("=======上传文件到服务器 submitUpload=======")
        this.$refs.upload.submit();
      },
      // 文件上传成功时的函数
      handleFileUploadSuccess (file) {
        console.log("=======文件上传成功时的函数 handleFileUploadSuccess=======")
        this.$message.success("上传成功")
      },
    },
  }
</script>
