const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer:{
    proxy:'http://localhost:8081',
    port:8081
  },
  transpileDependencies: true
})
