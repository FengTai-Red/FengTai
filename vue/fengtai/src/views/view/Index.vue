<template>
  <div >
    <!-- 顶部 -->
    <header>
      <a href="#" class="title router-link-active">风台</a>
      <span class="subtitle">风吹黑云雨 台上无纤埃</span> 
      <nav>
        <router-link v-for="(item, index) in routes[0].children" :key="item" :index="index+''" :to="item" v-show="item.show">
          <i :class="item.icon"></i> 
          <p>{{item.name}}</p>
        </router-link>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
    <!-- 底部 -->
    <div class="to_top">
      <a href="#top">
        <div>
          <svg width="28" height="28" fill="none" viewBox="0 0 24 24">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M4.75 19.25L12 4.75L19.25 19.25L12 15.75L4.75 19.25Z"></path>
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M12 15.5V12.75"></path>
          </svg>
        </div>
      </a>
    </div>
    <footer>
      <div class="site-info">
        <p><i class="icon icon-copyright"></i> <i class="icon icon-heart"></i> 风台 </p>
        <p> Theme - <a rel="noopener noreferrer" target="_blank" href="https://github.com/chanshiyucx/aurora">Aurora</a> | 风吹黑云雨<a @click="postadmin()">·</a>台上无纤埃 </p>
      </div>
    </footer>
  </div>
</template>

<script>
  import { defineComponent} from 'vue';
  import { useRouter } from 'vue-router';
  export default defineComponent({
    setup() {
      const router = useRouter();
      var routes = router.options.routes
      return {
        routes,
      };
    },
    methods: {
      postadmin(){
        // console.log(window.location.href);
        this.$router.push({
          path: "/Login",
        })
      },
    },
  });
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
