<!--  -->
<template>
  <el-form ref="updateForm" :model="updatePost" :rules="rules" label-width="80px">
    <el-form-item label="标题" prop="title">
      <el-input v-model="updatePost.title" maxlength="23"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="description">
      <el-input v-model="updatePost.description" maxlength="113"></el-input>
    </el-form-item>
    <el-form-item label="内容" maxlength="10240">
      <div style="border: 1px solid #ccc">
        <Toolbar
          style="border-bottom: 1px solid #ccc"
          :editor="editorRef"
          :defaultConfig="toolbarConfig"
          :mode="mode"
        />
        <Editor
          style="height: 450px; overflow-y: hidden;"
          v-model="updatePost.content"
          :defaultConfig="editorConfig"
          :mode="mode"
          @onCreated="handleCreated"
        />
      </div>
    </el-form-item>
    <el-form-item label="首图" prop="firstPicture">
      <el-select v-model="updatePost.firstPicture" class="m-2" placeholder="Select">
        <el-option v-for="item in postFirstPictureOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="分类" prop="category">
      <el-select v-model="updatePost.category" class="m-2" placeholder="Select">
        <el-option v-for="item in postCategoryOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item >
    <el-form-item label-width="auto" label="是否为发布状态" prop="delivery">
      <el-switch v-model="updatePost.published"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(updatePost)">更新</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  const postFirstPictureOptions = Array.from({ length: 90 }).map((_, idx) => ({
    value: `${idx + 10}.jpg`,
    label: `${idx + 10}.jpg`,
  }))

  import { useRouter } from 'vue-router';
  import axios from "axios"
  import '@wangeditor/editor/dist/css/style.css' // 引入 css
  import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
  import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
  var controllerPath = ''
  
  export default {
    created(){
      controllerPath = this.$config.controllerPath
      const _this = this
      axios.get(controllerPath + '/post/' + this.$route.query.id).then(function(resp){
        _this.updatePost = resp.data;
      })
      axios.get(controllerPath + '/category/findAll').then(function (resp){
         _this.postCategoryOptions = Array.from({ length: resp.data.length }).map((_, idx) => ({
          value: `${resp.data[idx].name}`,
          label: `${resp.data[idx].name}`,
        }))
      })
    },
    components: { Editor, Toolbar },
    setup() {
      // 编辑器实例，必须用 shallowRef
      const editorRef = shallowRef()
      const toolbarConfig = {}
      const editorConfig = { placeholder: '请输入内容...' }

      // 组件销毁时，也及时销毁编辑器
      onBeforeUnmount(() => {
          const editor = editorRef.value
          if (editor == null) return
          editor.destroy()
      })

      const handleCreated = (editor) => {
        editorRef.value = editor // 记录 editor 实例，重要！
      }

      return {
        editorRef,
        mode: 'default', // 或 'simple'
        toolbarConfig,
        editorConfig,
        handleCreated,
      };
    },
    data () {
      return {
        postCategoryOptions: '',
        postFirstPictureOptions,
        updatePost:{
          title: '',
          description: '',
          content: '',
          firstPicture: '',
          category: '',
          published: '',
        },
        rules: {
          title: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          firstPicture: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
          category: [
            { required: true, message: '不能为空', trigger: 'blur'},
          ],
        },
      }
    },
    methods: {
      submitForm(updatePost){
        const that = this
        this.$refs.updateForm.validate((valid) => {
          if (valid) {
            const _this = this
            axios.post(controllerPath + '/admin/post/save', updatePost).then(function(resp) {
              if (resp.data == 'success'){
                console.log('成功');
                that.$router.push({
                  path: "/admin/PostList",
                })
              }else{
                alert('新增失败')
              }
            })
          } else {
            console.log("校验失败");
          }
        })
      },
    }
  }
</script>

<style  scoped>

</style>
