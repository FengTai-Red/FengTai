<!--  -->
<template>
  <div>
    <!-- 主体 -->
    <main class="main">
      <div id="laboratory">
        <div class="page">
          <div class="quote">
            <i class="icon icon-quote-left"></i>
            <span>夢想時空 竹取飛翔</span>
            <i class="icon icon-quote-right"></i>
          </div>
          <ul class="content">
            <li class="cursor" v-for="(laboratory, index) in laboratorylist" :key="laboratory" :index="index+''">
              <a @click="postlaboratory(laboratory.path)">
              <img :src="require('@/assets/image/' + categoryBgOptions[index].value)" alt="" class="bg">
              <div class="meta">
                <div>
                  <img :src="require('@/assets/image/icon/' + categoryIconOptions[index].value)" alt="" class="avatar">
                  <span>{{laboratory.name}}</span>
                </div>
                  <p>{{laboratory.description}}</p>
              </div>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import { useRouter } from 'vue-router';
  export default {
    created(){
    },
    setup() {
        const router = useRouter();
      var routes = router.options.routes
      var laboratorylist = routes[1].children;
      return {
        laboratorylist, 
      };
    },
    data(){
      return {
        categoryIconOptions: this.$config.categoryIconOptions,
        categoryBgOptions: this.$config.categoryBgOptions,
      }
    },
    methods: {
      postlaboratory(path){
        this.$router.push({
          path: path,
        })
      },
    },
  }
</script>

<style  scoped>

</style>
