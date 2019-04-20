cd ktv-browser
echo "installing browser's dependencies ..."
cnpm install
echo "build browser app ..."
npm run build
cp dist/* ../ktv-server/core/src/main/webapp
cd ../ktv-server
echo "build server app ..."
mvn install -DskipTests=true
mv core/target/ktv.war ../ROOT.war

