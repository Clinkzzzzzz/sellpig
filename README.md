# sellpig
前后端分离的微信点餐系统

仿慕课网项目，只包含后端管理系统代码，无前端代码。</br>
前端：vue-webapp</br>
后端：springboot bootstrap+freemarker+jquery</br>
前后端用RESTful风格接口相连</br>
数据库：MySQL，Redis</br>
微信的登录，支付，退款功能因无公众哈采用测试号测试。</br>
角色划分：</br>
买家（手机端，微信），卖家（PC端）。</br>

部署架构：</br>
                      前端资源     转发     后端资源</br>
卖家端/买家端   --->  Nginx 服务器-------->tomcat服务器------>redis/mysql</br>
