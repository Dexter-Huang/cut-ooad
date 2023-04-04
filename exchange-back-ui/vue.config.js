const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer:{
    proxy:'http://localhost:8082',
    port:8082
  },
  transpileDependencies: true
})
