import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/views/Login.vue";
import Home from "@/views/Home.vue";
import CreateOrder from "@/views/CreateOrder.vue";
import Waiting from "@/views/Waiting.vue";
import InTransit from "@/views/InTransit.vue";
import MyHistory from "@/views/MyHistory.vue";
import Receive from "@/views/Receive.vue";
import MyInTrans from "@/views/MyInTrans.vue";
import TransHistory from "@/views/TransHistory.vue";

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        children:[
            {
                path: '/createorder',
                name: 'CreateOrder',
                component: CreateOrder
            },
            {
                path: '/waiting',
                name: 'Waiting',
                component: Waiting
            },
            {
                path: '/intransit',
                component: InTransit
            },
            {
                path: '/myhistory',
                component: MyHistory
            },
            {
                path: '/receive',
                component: Receive
            },
            {
                path: '/myintrans',
                component: MyInTrans
            },
            {
                path: '/transhistory',
                component: TransHistory
            }

        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router