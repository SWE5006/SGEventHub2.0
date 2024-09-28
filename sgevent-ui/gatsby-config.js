const { createProxyMiddleware } = require("http-proxy-middleware");
/**
 * @type {import('gatsby').GatsbyConfig}
 */
module.exports = {
  developMiddleware: (app) => {
     // 代理到 Event 微服务 (8081)
    app.use(
      "/api/event-manager",
      createProxyMiddleware({
        target: "http://localhost:8081", //event-manager microservices
        pathRewrite: { "^/api/event-manager": "/api/event-manager" },  // 去掉路径前缀
        changeOrigin: true,
      })
    );
     // 代理到 User 微服务 (8082)
    app.use(
      "/api/user-manager",
      createProxyMiddleware({
        target: "http://localhost:8082", //user-manager microservices
        pathRewrite: { "^/api/user-manager": "/api/user-manager" },  // 去掉路径前缀
        changeOrigin: true,
      })
    );
      app.use(
        "/sgMap",
        createProxyMiddleware({
          target: "https://www.onemap.gov.sg/api/common/elastic",
          secure: false,
          changeOrigin: true,
          pathRewrite: {
            "^/sgMap": "",
          },
        })
      );
  },
  siteMetadata: {
    title: `community-event`,
    siteUrl: `https://www.yourdomain.tld`,
  },
  plugins: [
    "gatsby-plugin-sitemap",
    {
      resolve: `gatsby-plugin-s3`,
      options: {
        bucketName: "sg-event-hub-ui-2",
      },
    },
  ],
};
