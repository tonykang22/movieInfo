# MovieInfo






## build information

### gradle build
```
$ ./gradlew build
```

### To run WAS (../build/libs)
```
$ java -jar filename.jar
```




## 서비스 사용 방법

### - Popular List: 인기 영화 순으로 view에 display
```
api/popular/{page}
```

### - Popular List의 Movie Info: 간략한 영화의 정보
```
api/popular/{page}/movie/{whichOne}
```

### - MovieInfo : 자세한 영화의 정보 
```
api/movie/{movieId}
```
