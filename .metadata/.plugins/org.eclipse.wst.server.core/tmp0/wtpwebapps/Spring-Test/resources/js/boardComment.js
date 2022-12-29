async function postCommentToServer(cmtData){
    try {
        const url = '/comment/post';
        const config = {
            method : 'post',
            headers : { 
            'content-type' : 'application/json; charset=UTF-8'},
            body : JSON.stringify(cmtData)
        };
        console.log(url);
        console.log(config);
        const resp = await fetch(url, config);
        const result  = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText =  document.getElementById('cmtText').value;
    console.log(cmtText);
    if(cmtText==null || cmtText==''){
        alert("댓글을 입력해주세요.");
        document.getElementById('cmtText').focus(); //커서를 cmtText창으로 
        return false;
    }else{
        let cmtData = {
            bno : bnoVal,
            writer : document.getElementById('cmtWriter').innerText,
            content : cmtText
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result =>{
            if(result>0){
                alert("댓글 등록 성공");
            }
            // 화면에 출력
            getCommentList(cmtData.bno);
            
        });
    }
});

async function spreadCommentFromServer(bno){
    console.log(bno);
    try {
        const resp = await fetch('/comment/'+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function getCommentList(bno){
    spreadCommentFromServer(bno).then(result =>{
        console.log(result);
        const ul = document.getElementById('cmtListArea');
        if(result.length > 0){ //값이 있다면
            ul.innerHTML = ""; //기존에 있던거 지우고
            for(let cvo of result){
                let li = `<li data-cno="${cvo.cno}" class="list-group-item d-flex justify-content-between align-items-start">`;
                    li+= `<div class="ms-2 me-auto"> <div class="fw-bold">${cvo.writer}</div>`;
                    li+= `<input type="text" class="form-control" id="cmtTextMod" value="${cvo.content}"></div>`;
                    li+= `<span class="badge bg-dark rounded-pill">${cvo.mod_at}</span>`;    
                    //수정 버튼 , 닫기 버튼 추가
                    li+= `<button class="btn btn-sm btn-outline-warning mod" type="button">%</button>`;
                    li+= `<button class="btn btn-sm btn-outline-danger del" type="button">X</button>`;
                    li+= `</li>`;
                    ul.innerHTML += li; //cvo가 생길때마다 하나씩 추가
            }
        }else{
            let li = `<li class="list-group-item d-flex justify-content-between align-items-start">Comment Null</li>`
            ul.innerHTML += li;
        }
    })
}

async function editCommentToServer(cmtDataMod){
    try {
        const url = '/comment/'+cmtDataMod.cno;
        const config = {
            //put은 전체수정, patch은 부분수정
            method : 'put',
            headers : { 
                'content-type' : 'application/json; charset=UTF-8'
            },
            body : JSON.stringify(cmtDataMod)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('mod')){
        //closest : 내가 가지고 있는 li중에서 가장 가까운(첫번째) li를 가져와라
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        let textContent = li.querySelector('#cmtTextMod').value;

        let cmtDataMod = {
            cno : cnoVal,
            content : textContent
        };
        console.log(cmtDataMod);
        editCommentToServer(cmtDataMod).then(result => {
            if(result>0){
                alert("댓글 수정 성공");
            }
            getCommentList(bnoVal);
        });
    }else if(e.target.classList.contains('del')){
        //삭제값 처리
            let li = e.target.closest('li');
            let cnoVal = li.dataset.cno;
            deleteCommentToServer(cnoVal).then(result =>{
                if(result>0){

                    alert("댓글 삭제 성공");
                }
                getCommentList(bnoVal);
            })
    }
}
)
async function deleteCommentToServer(cnoVal){
    try {
        const url = '/comment/'+cnoVal;
        const config = {
            method : 'delete',
            headers : { 
                'content-type' : 'application/json; charset=UTF-8'
            },
            body : JSON.stringify(cnoVal)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}