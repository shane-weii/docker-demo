#########################################################################
# File Name: init.sh
# Author: ststorytony
# mail: ststorytony@gmail.com
# Created Time: Sat 15 Jun 2019 02:37:18 PM CST
#########################################################################
#!/bin/bash
USER="root";
PASSWORD="123456";

mysql -u $USER -p$PASSWORD <<EOF
source /home/script/init.sql
EOF
