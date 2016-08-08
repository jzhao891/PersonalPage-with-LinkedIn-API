//////image
var styleImg = {
    width: "200px"
};
var Image=React.createClass({
  loadImgFromServer:function(){
    $.ajax({
      type:"GET",
      url:this.props.url,
      dataType:'json',
      cache:false,
      success: function(data){
        this.setState({data:[data]});
      }.bind(this)
    });
  },
  getInitialState:function(){
	    return{data:[]};
  },
  componentDidMount:function(){
	  this.loadImgFromServer();
  },
  render: function(){
	  console.log(this.state.data);
    var imgUrl=this.state.data.map(function(d){
      return(
        d.pictureUrl
      );
    });
    return(
    <div>
      <img alt="photo" src={imgUrl} style={styleImg}/>
    </div>
    );
  }

});
ReactDOM.render(
  <Image url="GetImg" />,
  document.getElementById('img')
);
