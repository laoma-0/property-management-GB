import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { resolve } from "path"; // 修改这行：从path模块导入resolve方法

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
      "@": resolve(__dirname, "./src"), // 修改这行：使用resolve方法替代path.resolve
    },
    extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"],
  },
  assetsInclude: ["**/*.Vue"],
});