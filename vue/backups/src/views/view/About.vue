<!--  -->
<template>
  <div>
    <main class="main">
      <div id="about">
        <div class="page">
          <div class="quote">
            <i class="icon icon-quote-left"></i>
            <span>追想風景 彼岸帰航</span>
            <i class="icon icon-quote-right"></i>
          </div>
          <div class="content">
            <!-- 个人简介 -->
            <div class="header">
              <img img :src="require('@/assets/image/icon/' + info.avatar)">
              <!-- 个人信息 -->
              <div class="info">
                <span><i class="icon icon-fort-awesome"></i>{{info.title}}</span>
                <span><i class="icon icon-pagelines"></i>{{info.subtitle}}</span>
                <span><i class="icon icon-graduation-cap"></i>{{info.motto}}</span>
              </div>
            </div>
            <!-- 各种网站链接 -->
            <div class="contact">
              <a v-for="(contac, index) in info.contactData" :key="contac" :index="index+''"  @click="toHref(contac.path)">
                <img :src="require('@/assets/image/icon/' + contac.icon)">
              </a>
            </div>
            <!-- 文字 -->
            <div class="segment" :style="{borderColor: colors[index % 12]}" v-for="(segment, index) in info.segmentData" :key="segment" :index="index+''">
              <div class="label" :style="{color: colors[index % 12]}">
                <span>{{segment.label}}</span>
              </div>
              <div class="markdown">
                <p style="padding-left: 30px;">{{segment.markdown}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
  import { shuffle } from '@/utils';
  export default {
    data() {
      var colors = shuffle(this.$config.themeColors);
      const info = {
        title: this.$config.title,
        subtitle: this.$config.subtitle,
        avatar: this.$config.aboutOpts.avatar,
        motto: this.$config.aboutOpts.motto,
        contactData: this.$config.aboutOpts.contact,
        segmentData: this.$config.aboutOpts.segment,
      }
      return {
        info,
        colors,
      }
    },
    methods: {
      toHref(path) {
        window.open(path);
      },
    },
  }
</script>

<style  scoped>

</style>
