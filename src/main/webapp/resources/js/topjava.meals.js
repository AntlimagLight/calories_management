const mealAjaxUrl = "ajax/meals/";

const ctx = {
    ajaxUrl: mealAjaxUrl
};

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: ctx.ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(function () {
        updateTableByData();
        successNoty("Filtered");
    });
}

function updateTableByData(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        })
    );
});