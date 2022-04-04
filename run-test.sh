docker build -t practis-tests . --progress=plain

docker run --rm -v $PWD:/opt/tests practis-tests