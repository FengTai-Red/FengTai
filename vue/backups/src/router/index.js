import { createRouter, createWebHistory } from 'vue-router'
import In from '../views/Blank.vue'

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
        path: "/CategoryList",
        name: "分类列表",
        icon: "",
        qoute: "華枝春滿 天心月圓",
        show: false,
        component: () => import("../views/view/CategoryList.vue")
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
        path: "/Laboratory",
        name: "实验",
        icon: "icon icon-heart",
        qoute: "夢想時空 竹取飛翔",
        show: true,
        component: () => import("../views/view/Laboratory.vue")
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
        path: "/Post",
        name: "文章",
        icon: "",
        qoute: "",
        show: false,
        component: () => import("../views/view/Post.vue")
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
    path: '/Laboratory',
    name: "测试",
    show: false,
    component: () => import("../views/Blank.vue"),
    children: [
      {
        path: '/Laboratory/Test',
        name: '测试',
        description: '测试用',
        component: () => import("../views/view/laboratory/Test.vue")
      },
      {
        path: '/Laboratory/BV2AV',
        name: 'BV号转AV号',
        description: '将BV号转为AV号',
        component: () => import("../views/view/laboratory/BV2AV.vue")
      },
      {
        path: '/Laboratory/GenshinInfo',
        name: '原神信息',
        description: '统计原神账号信息',
        component: () => import("../views/view/laboratory/GenshinInfo.vue")
      },
      {
        path: '/Laboratory/IDmunber',
        name: '号码生成',
        description: '号码生成',
        component: () => import("../views/view/laboratory/IDmunber.vue")
      },
    ]
  },
  {
    path: '/Login',
    name: "登录",
    show: false,
    component:  () => import('../views/Blank.vue'),
    children: [
      {
        path: '/Login',
        name: '登录',
        component: () => import("../views/admin/Login.vue")
      },
    ]
  },
  {
    path: '/admin',
    name: "后台",
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
        path: '/admin/PostAdd',
        name: '新增文章',
        show: false,
        component: () => import("../views/admin/PostAdd.vue")
      },
      {
        path: '/admin/PostEdit',
        name: '编辑文章',
        show: false,
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

//还是在router.js中 我们这里就放在 export default router 的后面
//登录拦截
router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {  // 判断该路由是否需要登录权限
    //如果需要就执行下面的代码
    var num=sessionStorage.getItem("isLogin");
    // 通过sessionStorage 获取当前的isLogin的值 将我们保存的isLogin的值赋给num,num是顺便取的名称，可以换
    //我们在登录界面，我们使用请求，请求成功后，我们就使用sessionStorage为‘isLogin’保存一个值  1，如果请求失败，就不保存‘isLogin’的值
    //如果请求成功，num的值就是1，请求失败就是null，所以下面进行判断
    if (num==1) {  
      //如果登录了，就跳转到'/index'路径
      next();
    }
    else {
      next({
        path: '/Login',//返回登录界面
        // query: {redirect: to.fullPath}  
      })
    }
  }
  else {
    //如果不需要登录权限就直接跳转到该路由
    next();
  }
})