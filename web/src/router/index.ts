import {createRouter, createWebHashHistory, RouteRecordRaw} from 'vue-router'
import {isLogin} from "~/utils/auth";
import {ElMessage} from "element-plus";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/",
        component: () => import("~/views/index.vue")
    },
    {
        path: "/login",
        component: () => import("~/views/index.vue")
    },
    {
        path: "/register",
        component: () => import("~/views/user/Register.vue")
    },
    {
        path: "/userIndex",
        component: () => import("~/views/user/UserIndex.vue")
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes: routes
})
let baiMD = [
    ''
]
router.beforeEach((to, from, next) => {
    if (to.path.startsWith('/') || to.path.startsWith('/login') || to.path.startsWith('/register')) {
        next()
    } else {
        if (isLogin()) {
            next()
        } else {
            ElMessage.error('请先登录')
            next({
                path: '/login'
            })
        }
    }
})

export default router