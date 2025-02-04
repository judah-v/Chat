
let userId = document.getElementById("userId");
let messageInput = document.getElementById("messageInput");
let chatHistory = document.getElementById("chatHistory");


function updateHistory(){
    fetch("/history").then((results)=>{
            return results.json();

    }).then((history)=>{
        let lastMsg = chatHistory.lastElementChild;
        let lastMsgShownId = lastMsg? lastMsg.firstElementChild.firstElementChild.textContent : null;
        let lastMsgSent = history.at(-1)? history.at(-1) : null;
        let shownMessageIds = [];

        for (const row of chatHistory.children){
            shownMessageIds.push(row.firstElementChild.firstElementChild.textContent);
        }
        newMessages = history.filter((msg)=>{
            return !shownMessageIds.includes(msg.id);
            
        })

        newMessages.forEach((msg)=>{
            let row = document.createElement("div");
            row.classList.add("row", "w-100", "ps-3");

            let messageCard = document.createElement("div");
            messageCard.classList.add("card", "col", "col-7", "shadow", "my-1");
            
            let messageId = document.createElement("p");
            messageId.classList.add("d-none");
            
            let messageText = document.createElement("p");
            messageText.classList.add("fs-5");

            let messageSender = document.createElement("p");
            messageSender.classList.add("fs-6", "text-start");
            messageSender.style.fontWeight = "bold";

            messageId.textContent = msg.id;
            messageCard.appendChild(messageId);

            if (userId.value == msg.senderID){
                messageCard.classList.add('offset-5', 'bg-success-subtle');
                messageText.classList.add("text-end", "pt-3", "pe-2");
            } else {
                messageCard.classList.add('bg-info-subtle');
                messageText.classList.add("text-start", "ps-2");
                messageCard.appendChild(messageSender);
            }

            fetch("/name?userId="+msg.senderID).then((results)=>{
                return results.text();
            }).then((name)=>{
                
                if (userId.value != msg.senderID){
                    messageSender.textContent = name;
                }

                messageText.textContent = msg.text;
                messageCard.appendChild(messageText);
                row.appendChild(messageCard);
                chatHistory.appendChild(row);
                messageInput.focus();

            })
            
        })
        newMessages = [];

    })
    
}
setInterval(updateHistory, 2000);
// THIS KEEPS FAILING!!!