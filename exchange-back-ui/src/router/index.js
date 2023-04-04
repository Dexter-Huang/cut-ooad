import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: {name: "Login"}
  },
  {
    path:'/admin',
    name:'adminHome',
    component: ()=>import('../views/adminHome'),
    children:[
      {
        path: '/billInfo',
        name: 'billInfo',
        component: ()=>import('../views/bill/BillInfo')
      },
      {
        path: '/messageToUser',
        name: 'MessageToUser',
        component: ()=>import('../views/MessageToUser')
      },
      {
        path: '/userInfo',
        name: 'userInfo',
        component: ()=>import('../views/user/userInfo')
      },

      {
        path: '/notices',
        name: 'Notices',
        component: ()=>import('../views/notice/Notices')
      },
      {
        path: '/notice/add',
        name: 'NoticeAdd',
        component: ()=>import('../views/notice/NoticeEdit'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/notice/:noticeId',
        name: 'NoticeDetail',
        component: ()=>import('../views/notice/NoticeDetail')
      },
      {
        path: '/notice/:noticeId/edit',
        name: 'NoticeEdit',
        component: ()=>import('../views/notice/NoticeEdit'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/imserver',
        name: 'Chat',
        component: ()=>import('../views/userChat'),
        meta: {
          requireAuth: true
        }
      },
      {
        path:'/map',
        name:'map',
        component: ()=>import('../views/Map'),
      },
      {
        path:'/backchat',
        name:'backchat',
        component: ()=>import('../views/BackChat'),
      },
    ]
  },

  {
    path: '/account/login',
    name: 'Login',
    component: ()=>import('../views/login/Login')
  },
  {
    path: '/account/forgetpassword',
    name: 'ForgetPassword',
    component: ()=>import('../views/login/ForgetPassword')
  },
  {
    path: '/account/register',
    name: 'Register',
    component: ()=>import('../views/login/Register')
  },
  {
    path: '/account/resetpasswword',
    name: 'ResetPassword',
    component: ()=>import('../views/login/ResetPassword'),
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
