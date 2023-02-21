# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER deng
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD /ai-api-interfaces/target/ai-api.jar /ai-api.jar
# 执行镜像；docker run -e PARAMS=" --ai-api.groupId=你的星球ID --ai-api.openAiKey=自行申请 --ai-api.cookie=登录cookie信息" -p 8090:8090 --name ai-api -d ai-api:1.0
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /ai-api.jar $PARAMS"]
