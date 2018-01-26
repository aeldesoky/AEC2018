/**
 * This method downloads the markdown file, converts it to html and appends it to the correct html item.
 * @type {Converter}
 */
var converter = new showdown.Converter();
var md = '[**Showdown**](http://www.showdownjs.com) is *great*\n' +
    'because:\n\n' +
    ' - it\'s easy to use\n' +
    ' - it\'s extensible\n' +
    ' - works in the server and in the browser';
var html = converter.makeHtml(md);