$(function() {
    $('#addQuizForm\\:quizDate, #editQuizForm\\:quizDate').datetimepicker({
        format: 'd-m-Y H:i'
    });
    $('#quizAttemptForm input[type=radio]').first().attr('checked', 'checked');
});



