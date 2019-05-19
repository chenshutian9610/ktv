cd ktv-browser
echo "installing browser's dependencies ..."
cnpm install
echo "build browser app ..."
npm run build
cp -r dist/* ../ktv-server/core/src/main/webapp
cd ../ktv-server
echo "build server app ..."
mvn install -DskipTests=true

