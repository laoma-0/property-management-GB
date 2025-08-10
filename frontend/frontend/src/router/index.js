import { createRouter, createWebHistory } from "vue-router";
const routes = [
  {
    path: "/",
    name: "Home",
    component: () => import("../views/Home.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/user",
    name: "User",
    component: () => import("../views/User.vue"),
  },
  {
    path: "/property",
    name: "Property",
    component: () => import("../views/Property.vue"),
  },
  {
    path: "/repair",
    name: "Repair",
    component: () => import("../views/Repair.vue"),
  },
  {
    path: "/payment",
    name: "Payment",
    component: () => import("../views/Payment.vue"),
  },
  {
    path: "/announcement",
    name: "Announcement",
    component: () => import("../views/Announcement.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
