// src/store/userStore.js
import { defineStore } from 'pinia'
import { ref, watch } from 'vue'
import { login as loginApi, getUserInfo as getUserInfoApi } from '@/api/auth'

export const useUserStore = defineStore('user', () => {
    const userInfo = ref(null)
    const token = ref(localStorage.getItem('token') || '')

    // 监听token变化，同步到localStorage
    watch(token, (newToken) => {
        if (newToken) {
            localStorage.setItem('token', newToken)
        } else {
            localStorage.removeItem('token')
        }
    })

    // 监听userInfo变化，可以做一些额外处理
    watch(userInfo, (newUserInfo) => {
        // 可以在这里添加用户信息变化时的处理逻辑
    })

    // 登录
    const login = async (username, password) => {
        const res = await loginApi({ username, password })
        token.value = res.token
        // 如果返回的用户信息在res.user中，则直接使用
        // 否则需要单独获取用户信息
        if (res.user) {
            userInfo.value = res.user
        } else {
            // 获取用户信息
            await getUserInfo()
        }
        localStorage.setItem('token', res.token)
        // 记录登录时间
        localStorage.setItem('loginTime', new Date().toISOString())
        return res
    }

    //  logout
    const logout = () => {
        token.value = ''
        userInfo.value = null
        localStorage.removeItem('token')
        localStorage.removeItem('loginTime')
    }

    // 检查登录是否过期（第二天0点自动退出）
    const isLoginExpired = () => {
        const loginTime = localStorage.getItem('loginTime')
        if (!loginTime) return true
        
        const loginDate = new Date(loginTime)
        const now = new Date()
        
        // 如果登录日期不是今天，则认为已过期
        return loginDate.toDateString() !== now.toDateString()
    }

    // 获取用户信息（用于刷新页面后恢复状态）
    const getUserInfo = async () => {
        // 检查是否过期
        if (isLoginExpired()) {
            logout()
            return null
        }
        
        if (!token.value) return null
        try {
            const res = await getUserInfoApi()
            userInfo.value = res.data || res.user || null // 兼容不同的返回格式
            return userInfo.value
        } catch (error) {
            // 如果获取用户信息失败，清除登录状态
            logout()
            throw error
        }
    }

    return { userInfo, token, login, logout, getUserInfo, isLoginExpired }
})