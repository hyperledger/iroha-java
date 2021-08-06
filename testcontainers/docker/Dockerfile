#ATTENTION PLEASE
#this is my image and it should be changed to official before merging!!!!
FROM pawlak00/my_iroha_builds:1.2.x
WORKDIR /opt/iroha_data
ENTRYPOINT [""]
COPY run-iroha.sh wait-for-it.sh /
RUN chmod +x /run-iroha.sh; \
    chmod +x /wait-for-it.sh; \
    apt-get install -y jq

CMD ["/run-iroha.sh"]
