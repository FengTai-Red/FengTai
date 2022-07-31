<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    data () {
      return {
      }
    }
  }
</script>

<style lang="scss" scope>
@import '@/assets/font/fontello.scss';
@import '@/assets/css/index.scss';
#app {
  position: relative;
  @include pc-layout {
    padding-bottom: 100px;
  }
  @include sp-layout {
    padding-bottom: 60px;
  }

  .main {
    margin: 0 auto;
    max-width: $page-desktop;
    user-select: text;
  }
}
</style>
