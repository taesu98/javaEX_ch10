function validateBook() {
    if (!$('#title').val()) {
        throw 'titleRequired';
    }
}

function updateBook(event) {
    try {
        validateBook();

        $('#book_form_action').val('setBook');
        $('#book_form')[0].submit();
    } catch (e) {
        switch (e) {
            case 'titleRequired':
                alert('제목을 입력해 주십시오.');
                break;
        }
        event.preventDefault();
    }
}

function deleteBook() {
    $('#book_form_action').val('removeBook');
    $('#book_form')[0].submit();
}

$(function () {
    $('#update_book_button').click(updateBook);
    $('#delete_book_button').click(deleteBook);
   
});