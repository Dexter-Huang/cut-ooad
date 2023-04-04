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
        path:'/userGiftCard',
        name:"userGiftCard",
        component: ()=>import('../views/user/userGift/userGift'),
      },
      {
        path:'/giftCards',
        name:"giftCards",
        component: ()=>import('../views/hotel/GiftCard/GiftCards'),
      },
      {
        path:'/userGift',
        name:"userGift",
        component: ()=>import('../views/user/userGift/userGift'),
      },
      {
        path:'/userMessage',
        name:"userMessage",
        component: ()=>import('../views/user/userMessage/userMessage'),
      },

      {
        path: '/account/reloginnotice',
        name: 'ReLoginNotice',
        component: ()=>import('../views/login/ReLoginNotice'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/account/reloginnotice',
        name: 'ReLoginNotice',
        component: ()=>import('../views/login/ReLoginNotice'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/userInfo',
        name: 'userInfo',
        component: ()=>import('../views/user/userInfo'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/userNews',
        name: 'userNews',
        component: ()=>import('../views/user/userMessage/userMessage'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/userCredit',
        name: 'userCredit',
        component: ()=>import('../views/user/userCredit/userCredit'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/userBill',
        name: 'userBill',
        component: ()=>import('../views/user/userBill/userBill'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/hotelInfo',
        name: 'hotelInfo',
        component: ()=>import('../views/hotel/HotelInfo')
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
        component: ()=>import('../views/user/userChat/UserChat'),
        meta: {
          requireAuth: true
        }
      },
      {
        path: '/serveChat',
        name: 'ServeChat',
        component: ()=>import('../views/user/userChat/ServeChat'),
        meta: {
          requireAuth: true
        }
      },
      {
        path:'/map',
        name:'map',
        component: ()=>import('../views/map/Map'),
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
