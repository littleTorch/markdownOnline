const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    open: true,
    port: 80,
    // hotOnly: false,
    // disableHostCheck: true,
    proxy: {
        '/api': {
            target: 'http://localhost:8000',
            //公网地址
            // http://sm2.v.frp.fit，
            changeOrigin: true,// 允许跨域
            ws: true, //如果要代理 websockets，配置这个参数
            pathRewrite: {
                '^/api': ''
            }
        }
    }
  }
})
