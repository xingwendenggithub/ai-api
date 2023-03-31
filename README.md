# ai-api
智能助手

# # 1. 项目介绍
❤️ 《ChatGPT AI 智能助手》 开源免费项目，涵盖爬虫接口、ChatGPT API对接、DDD架构设计、镜像打包、Docker容器部署，小巧精悍，流程全面。对于Java编程伙伴来说，非常具有学习价值。

 这个项目本身是我根据小傅哥的chatgpt ai 的项目进行学习自己一步步coding的一个智能问答回复系统，用于帮助读者解决一些常见的技术问题，提高回答效率。

《ChatGPT AI 智能助手》这样一个项目，要用到哪些技术手段呢？它包含；SpringBoot、DDD架构、Github仓库使用、接口爬虫、AI接口对接、定时任务、镜像打包、Docker容器部署等内容。

 
包括工程的创建、Github仓库使用、push代码等，因为只有这样才能让更多新人有一条进入学习编程的大门。

注意：

技术栈：Java、SpringBoot、爬虫、ChatGPT、job、Docker
OpenAi Keys 申请：https://beta.openai.com/account/api-keys - 用于处理扫码知识星球问题进行调用获取答案。
在学习的过程中，可以看到每一个章节都有一个对应的代码分支，可以把代码拉取到本地切换到对应的分支进行对照学习。

 ❤️❤️  加入星球【ChatGPT AI 问答助手】
 
PC端访问： https://wx.zsxq.com/dweb2/index/group/88885822524112

APP端提问：你可以通过微信扫码，加入知识星球【AI元空间】，在手机端对ChatGPT进行提问。
![海报 (2)](https://user-images.githubusercontent.com/19941718/229061163-fa0fe4ff-894f-44b9-9c12-b0f7941f27ff.png)
 




# # 2. 模型训练
  # #  2.1 环境安装
下载 Python：https://www.python.org/downloads/macos/ 3.6版本以上

配置 Python：

搜索地址 which python3
环境配置 alias python="/Library/Frameworks/Python.framework/Versions/3.10/bin/python3"
生效配置 source .bash_profile
安装 pip：以下需要用到 pip 指令，如果没有需要安装 'curl https://bootstrap.pypa.io/get-pip.py | python3'

# #  2.2 tensorflow
地址：https://www.tensorflow.org/install?hl=zh-cn

脚本：
```
  #Requires the latest pip
  
 pip install --upgrade pip

 #Current stable release for CPU and GPU
 
 pip install tensorflow

 #Or try the preview build (unstable)
 
 pip install tf-nightly
```
mac m1：python3 -m pip install tensorflow-macos

测试：
```
python3 -c "import tensorflow as tf; print(tf.reduce_sum(tf.random.normal([1000, 1000])))"

结果；tf.Tensor(228.22836, shape=(), dtype=float32)
```
# #  2.3 说明
如果在使用过程中遇到问题，也可以加我的个人微信进行咨询：WD-832372

