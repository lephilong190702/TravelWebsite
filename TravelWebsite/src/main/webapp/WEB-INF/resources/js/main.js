function deleteTour(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Lỗi!!!");
        });
    }
}

function deleteNew(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Lỗi!!!");
        });
    }
}

function loadComments(path) {
    fetch(path).then(function (res) {
        return res.json();
    }).then(function (data) {
        let c = document.getElementById("comments");
        let h = '';
        for (let d of data){
            h += `
                <li class="list-group-item">   
                    <img src="${d.userId.userImageurl}" alt="avatar" class="avatar"/>
                    <em>  ${d.commentContent}</em> 
                    <p>được bình luận bởi <strong>${d.userId.userUsername}</strong> vào <strong>${moment(d.commentDatetime).locale("vi").fromNow()}</strong></p>
                </li>
            `;
    }
        c.innerHTML = h;
    });
}

function addComment(path, tourId) {
    console.log("Textarea Value:", document.getElementById("contentId").value)
    fetch(path, {
        method: "post",
        body: JSON.stringify({
            "commentContent": document.getElementById("contentId").value,
            "tourId": tourId
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        let d = document.querySelector("#comments li:first-child");
        let h = `
                <li class="list-group-item"><em>${data.commentContent}</em> được bình luận bởi <strong>${data.userId.userUsername}</strong> vào <strong>${moment(d.commentDatetime).locale("vi").fromNow()}</strong>
            `;
        d.insertAdjacentHTML("beforebegin", h);
    });
}