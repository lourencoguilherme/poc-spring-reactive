FROM ubuntu:latest

WORKDIR /root

COPY ./build/native/nativeCompile/application /root

COPY entrypoint.sh /root/

ENTRYPOINT ["/root/entrypoint.sh"]
