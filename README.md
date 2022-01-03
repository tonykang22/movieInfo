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

### - Movie List: 영화 List 불러오기
```
/movies
```

### - Movie Info: 영화 Details 불러오기
```
/movies/{movieId}
```

### - Popular List: 인기 순으로 정렬한 영화 List(20 movies per page) 불러오기
```
/moives/popular?page={page}
```


