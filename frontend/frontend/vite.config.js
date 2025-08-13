import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "path"; // 新增这行：导入path模块

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    },
  },
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "./src"), // 这里用到了path，必须先导入
    },
    extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"],
  },
  assetsInclude: ["**/*.Vue"],
});