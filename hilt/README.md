# Hilt 예제

# ✌ Topic  
Riot API를 사용해 친구들의 전적을 저장하고 전적을 확인하는 어플리케이션  

# 😊 화면 구성 요소  
<img width="360" alt="스크린샷 2022-04-19 오후 1 21 36" src="https://user-images.githubusercontent.com/59405161/163919765-7022a981-998d-4ca4-b2ff-6569d7a58954.png">
<img width="360" alt="스크린샷 2022-04-19 오후 1 21 44" src="https://user-images.githubusercontent.com/59405161/163919784-fc416189-3f61-497f-868c-45f2f8329dae.png">
<img width="360" alt="스크린샷 2022-04-19 오후 1 22 04" src="https://user-images.githubusercontent.com/59405161/163919725-e578b3db-778b-4737-88c4-3776318d995a.png">

### MainApplication


<img width="329" alt="image" src="https://user-images.githubusercontent.com/59405161/163920804-6610014b-ef62-47f1-9f4c-9cb0939df4c4.png">


### MainViewModel


<img width="442" alt="image" src="https://user-images.githubusercontent.com/59405161/163920857-f7817d8b-156a-4711-a642-d9d247664840.png">


### MainFragment


<img width="782" alt="image" src="https://user-images.githubusercontent.com/59405161/163920893-6a3e6085-eef7-45db-bc43-3a6c4bce336f.png">

### SummonerAdapter


<img width="927" alt="image" src="https://user-images.githubusercontent.com/59405161/163920941-02d6ba63-15dc-4ea4-8dc3-b84234130ed6.png">


### RoomModule

<img width="688" alt="image" src="https://user-images.githubusercontent.com/59405161/163920979-b96f4107-6288-4e29-b322-0955c0236765.png">


 # 😋 디자인 패턴 및 라이브러리  
 #### MVVM 아키텍처 적용
 #### Retrofit 2 : 서버 통신에 사용
 #### Hilt : DI를 위해 사용
 #### LiveData : 데이터 Observe를 위해 사용
 #### ViewModel : 긴 수명주기에 데이터를 담기 위해 사용
 #### Coroutine : 비동기 작업을 위해 사용
 #### Glide : 이미지 업로드를 위해 사용
 #### Room : 로컬 데이터베이스 데이터를 저장하기 위해 사용
 #### NavigationActivity : Base Code들과 Navigation 연동의 간소화를 위해 사용
 
 ## MVVM 패턴을 공부하시는 분들께 좋은 예시가 될 것이라고 예상합니다. 

