// src/store/userStore.js
import { defineStore } from 'pinia'
import { ref } from 'vue'
import request from '@/api/request'

export const useUserStore = defineStore('user', () => {
    const userInfo = ref(null)
    const token = ref(localStorage.getItem('token') || '')

    // 登录
    const login = async (username, password) => {
        const res = await request.post('/auth/login', { username, password })
        token.value = res.token
        userInfo.value = res.user
        localStorage.setItem('token', res.token)
        return res
    }

    //  logout
    const logout = () => {
        token.value = ''
        userInfo.value = null
        localStorage.removeItem('token')
    }

    // 获取用户信息（用于刷新页面后恢复状态）
    const getUserInfo = async () => {
        if (!token.value) return
        const res = await request.get('/auth/info')
        userInfo.value = res.data
    }

    return { userInfo, token, login, logout, getUserInfo }
})