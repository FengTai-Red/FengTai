<!--  -->
<template>
  <el-form ref="updateForm" :model="addPost" :rules="rules" label-width="80px">
    <el-form-item label="标题" prop="title">
      <el-input v-model="addPost.title" maxlength="23"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="description">
      <el-input v-model="addPost.description" maxlength="113"></el-input>
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
          v-model="addPost.content"
          :defaultConfig="editorConfig"
          :mode="mode"
          @onCreated="handleCreated"
        />
      </div>
    </el-form-item>
    <el-form-item label="首图" prop="firstPicture">
      <el-select v-model="addPost.firstPicture" class="m-2" placeholder="Select">
        <el-option v-for="item in postFirstPictureOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>
    <el-form-item label="分类" prop="category">
      <el-select v-model="addPost.category" class="m-2" placeholder="Select">
        <el-option v-for="item in postCategoryOptions" :key="item.value" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item >
    <el-form-item label-width="auto" label="是否为发布状态" prop="delivery">
      <el-switch v-model="addPost.published"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(addPost)">创建</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  const postFirstPictureOptions = Array.from({ length: 90 }).map((_, idx) => ({
    value: `${idx + 10}.jpg`,
    label: `${idx + 10}.jpg`,
  }))

  import axios from "axios"
  import '@wangeditor/editor/dist/css/style.css' // 引入 css
  import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
  import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

  export default {
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
        postCategoryOptions: [
          {value: '测试', label: '测试'},
          {value: 'ACG', label: 'ACG'},
          {value: 'Code', label: 'Code'},
        ],
        postFirstPictureOptions,
        addPost:{
          title: '',
          content: '测试content',
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
      submitForm(addPost){
        const that = this
        this.$refs.updateForm.validate((valid) => {
          if (valid) {
            const _this = this
            axios.post('http://127.0.0.1:8181/admin/post/save', addPost).then(function(resp) {
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
