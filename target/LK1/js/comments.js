var Comment = React.createClass({
  render: function() {
    return (
      <div className="comment">
        <h3 className="contentURL">
          {this.props.contentURL}
        </h3>
        <h4 className="contentTitle">
          {this.props.contentTitle}
        </h4>
        <h4>{this.props.description}</h4>
        <p>{this.props.childern.toString()}</p>//be careful toString()
      </div>
    );
  }
});

var CommentList = React.createClass({
  render: function() {
    var commentNodes = this.props.data.map(function (comment) {
     return (
       <Comment contentURL={comment.url} contentTitle={comment.title} description={comment.desc}>
         {comment.text}
       </Comment>
     );
   });
    return (
      <div className="commentList">
        {commentNodes}
      </div>
    );
  }
});

var CommentForm = React.createClass({
  handleSubmit: function(e) {
    e.preventDefault();
    var title = this.refs.title.value.trim();
    var desc = this.refs.desc.value.trim();
    var url = this.refs.url.value.trim();
    var text = this.refs.text.value.trim();
    if (!title|| !desc|| !url|| !text) {
      return;
    }
    // TODO: send request to the server
    this.props.onCommentSubmit({title:title,desc:desc,url:url,text:text})
    this.refs.title.value = '';
    this.refs.desc.value = '';
    this.refs.url.value = '';
    this.refs.text.value = '';
    return;
  },
  render: function() {
    return (
      <form className="commentForm" onSubmit={this.handleSubmit}>
        <input type="text" placeholder="The Title of Your Sharing" ref="title"/>
        <input type="text" placeholder="The Description" ref="desc"/>
        <input type="text" placeholder="The URL of Your Sharing" ref="url"/>
        <input type="text" placeholder="Say something..." ref="text"/>
        <input type="submit" value="Post" />
      </form>
    );
  }
});

var CommentBox = React.createClass({

  handleCommentSubmit:function(comment){
    // TODO: submit to the server and refresh the list
    var comments=this.state.data;
    var newComments=comments.concat([comment]);
    this.setState({data:newComments});
    $.ajax({
      url: this.props.url,
      dataType: 'json',
      type: 'POST',
      data: comment,
      success: function(data) {
        this.setState({data: data});//
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  },
  getInitialState:function(){
    return {data:[]};
  },
  componentDidMount:function(){
    $.ajax({
      url:this.props.url,
      dataType:'json',
      cache:false,
      success:function(data){
        this.setState({data:data})//be careful may data:[data]
      }.bind(this),
      error:function(xhr,status,err){
        console.error(this.props.url,status,err.toString())
      }.bind(this)
    });
  },
  render: function() {
    return (
      <div className="commentBox">
        <h2>Your Comments</h2>
        <CommentList data={this.state.data}/>
        <CommentForm onCommentSubmit={this.handleCommentSubmit}/>
      </div>
    );
  }
});
ReactDOM.render(
  <CommentBox url="Comment"/>,//need url
  document.getElementById('content')
);
