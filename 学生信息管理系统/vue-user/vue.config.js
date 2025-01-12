// const { defineConfig } = require('@vue/cli-service')
const webpack = require('webpack');
module.exports ={
    devServer: {
      port:7001,
      proxy: {
        '/api': {
          target:`http://localhost:8080`,
          changeOrgin: true,
          ws: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    },
  }

