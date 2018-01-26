/**
 * This method downloads the markdown file, converts it to html and appends it to the correct html item.
 * @type {Converter}
 */
var converter = new showdown.Converter();
$.ajax({
    url: "/static/md/docs.md",
    success: function(markdown){
        var html = converter.makeHtml(markdown);
        $("#markdown").append(html);
    }
});
