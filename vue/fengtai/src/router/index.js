import { createRouter, createWebHistory } from 'vue-router'
import In from '../views/In.vue'

const routes = [
  {
    path: '/',
    name: '首页',
    component: () => import('../views/view/Index.vue'),
    redirect: '/Blog',
    children: [
      {
        path: "/Blog",
        name: "博客",
        icon: "icon icon-shop",
        qoute: "",
        show: true,
        component: () => import("../views/view/Blog.vue")
      },
      {
        path: "/Category",
        name: "分类",
        icon: "icon icon-bookmark-empty",
        qoute: "華枝春滿 天心月圓",
        show: true,
        component: () => import("../views/view/Category.vue")
      },
      {
        path: "/Navigation",
        name: "导航",
        icon: "icon icon-inbox",
        qoute: "桜華月想 暮色蒼然",
        show: true,
        component: () => import("../views/view/Navigation.vue")
      },
      {
        path: "/Tool",
        name: "工具",
        icon: "icon icon-pencil",
        qoute: "詠奏妖華 明鏡止水",
        show: true,
        component: () => import("../views/view/Tool.vue")
      },
      {
        path: "/About",
        name: "关于",
        icon: "icon icon-universal-access",
        qoute: "追想風景 彼岸帰航",
        show: true,
        component: () => import("../views/view/About.vue")
      },
      {
        path: "/Test",
        name: "测试",
        icon: "icon icon-comment",
        qoute: "",
        show: false,
        component: () => import("../views/view/Test.vue")
      },
      {
        path: "/Post",
        name: "文章",
        icon: "",
        qoute: "",
        show: false,
        component: () => import("../views/view/Post.vue")
      },
      {
        path: "/CategoryList",
        name: "分类列表",
        icon: "",
        qoute: "華枝春滿 天心月圓",
        show: false,
        component: () => import("../views/view/CategoryList.vue")
      },
      {
        path: '/:pathMatch(.*)', //访问主页的时候 重定向到index页面
        redirect: '/404',
      },
      {
        path: '/404',
        name: '/404',
        component: () => import("../views/404.vue")
      }
    ]
  },
  {
    path: '/Login',
    name: "登录",
    show: false,
    component:  () => import('../views/In.vue'),
    children: [
      {
        path: '/Login',
        name: '登录',
        component: () => import("../views/Login.vue")
      },
    ]
  },
  {
    path: '/admin',
    name: "导航一",
    show: true,
    component: () => import('../views/admin/Index.vue'),
    meta:{
      requireAuth: true,
    },
    children: [
      {
        path: '/admin/PostList',
        name: '文章管理',
        show: true,
        component: () => import("../views/admin/PostList.vue")
      },
      {
        path: '/admin/CategoryList',
        name: '分类管理',
        show: true,
        component: () => import("../views/admin/CategoryList.vue")
      },
      {
        path: '/admin/NavigationList',
        name: '导航管理',
        show: true,
        component: () => import("../views/admin/NavigationList.vue")
      },
      {
        path: '/admin/ToolList',
        name: '工具管理',
        show: true,
        component: () => import("../views/admin/ToolList.vue")
      },
      {
        path: '/admin/Test',
        name: '测试',
        show: true,
        component: () => import("../views/admin/Test.vue")
      },
    ]
  },
  {
    path: '/hide',
    name: "隐藏的导航",
    show: false,
    component: () => import('../views/admin/Index.vue'),
    meta:{
      requireAuth: true,
    },
    children: [
      {
        path: '/admin/PostAdd',
        name: '新增文章',
        component: () => import("../views/admin/PostAdd.vue")
      },
      {
        path: '/admin/PostEdit',
        name: '编辑文章',
        component: () => import("../views/admin/PostEdit.vue")
      },
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
