# 写在最前面：强烈建议先阅读官方教程[Dockerfile最佳实践]（https://docs.docker.com/develop/develop-images/dockerfile_best-practices/）
# 选择构建用基础镜像（选择原则：在包含所有用到的依赖前提下尽可能提及小）。如需更换，请到[dockerhub官方仓库](https://hub.docker.com/_/java?tab=tags)自行选择后替换。
FROM maven:3.6.0-jdk-11-slim as build

# 指定构建过程中的工作目录
WORKDIR /app

# 将src目录下所有文件，拷贝到工作目录中src目录下
COPY src /app/src

# 将pom.xml文件，拷贝到工作目录下
COPY pom.xml /app

# 执行代码编译命令
RUN mvn -f /app/pom.xml clean package

# 选择运行时基础镜像。（JAVA语言基础镜像选择原则：业务所需openjdk版本中，尽可能用选体积小的）
FROM openjdk:8u111-jre-alpine

# 指定运行时的工作目录
WORKDIR /app

# 将构建产物jar包拷贝到运行时目录中
COPY --from=build /app/target/springboot-wxcloudrun-1.0.jar .

# 执行启动命令
ENTRYPOINT ["java","-jar","/app/springboot-wxcloudrun-1.0.jar"]

# 暴露端口
EXPOSE 80
