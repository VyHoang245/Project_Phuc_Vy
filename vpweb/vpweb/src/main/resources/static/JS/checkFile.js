let toastBox = document.getElementById('toastBox');
let successMg = '<i class="fa fa-check-circle" aria-hidden="true"></i>  Product added successfully getByID';
// let warningMg = '<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> Warning';
// let errorMg = '<i class="fa fa-times-circle" aria-hidden="true"></i> Error';

// document.getElementsByClassName('showToast').addEventListener('click', function () {
//     showToast(successMg);
// });

document.addEventListener("DOMContentLoaded", function () {
    // Add click event listener to the entire document
    document.body.addEventListener('click', function (event) {
        if (event.target.classList.contains('showToast')) {
            showToast(successMg);
        }
    });
});

function showToast(msg) {
    let toast = document.createElement('div');
    toast.classList.add('toast');
    toast.innerHTML = msg;
    toastBox.appendChild(toast);
    // if (msg.includes('Warning')) {
    //     toast.classList.add('warning');
    // }
    // if (msg.includes('Error')) {
    //     toast.classList.add('error');
    // }

    setInterval(() => {
        toast.remove();
    }, 2000)
}

// Toast 1
// let toastBox = $("#toastBox");
// let successMg = '<i class="fa fa-check-circle" aria-hidden="true"></i>  Product added successfully';
// $(".showToast").click(function () {
//     let toast = document.createElement('div');
//     toast.classList.add('toast');
//     toast.innerHTML = successMg;
//     toastBox.appendChild(toast);
//     // if (msg.includes('Warning')) {
//     //     toast.classList.add('warning');
//     // }
//     // if (msg.includes('Error')) {
//     //     toast.classList.add('error');
//     // }
//
//     setInterval(() => {
//         toast.remove();
//     }, 2000)
// })


// // Toast2
// $(document).ready(function () {
//     $("body").on("click", ".showToast", function () {
//         showToast(successMg);
//     });
// });
//
// function showToast(msg) {
//     const toastBox = $("#toastBox");
//     const toast = $('<div class="toast"></div>').html(msg);
//
//     // Append toast to the toastBox
//     toastBox.append(toast);
//
//     // Automatically remove toast after 2 seconds
//     setTimeout(() => {
//         toast.remove();
//     }, 2000);
//
//     // Optional: Add warning or error classes
//     // if (msg.includes('Warning')) {
//     //     toast.addClass('warning');
//     // }
//     // if (msg.includes('Error')) {
//     //     toast.addClass('error');
//     // }
//
//     // Close button functionality (if any close button is needed)
//     // const closeButton = $('<span class="toast-close">&times;</span>');
//     // closeButton.click(() => toast.remove());
//     // toast.append(closeButton);
// }
//
// const successMg = '<i class="fa fa-check-circle" aria-hidden="true"></i> Product added successfully';
// // const warningMg = '<i class="fa fa-exclamation-triangle" aria-hidden="true"></i> Warning';
// // const errorMg = '<i class="fa fa-times-circle" aria-hidden="true"></i> Error';
