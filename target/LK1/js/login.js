
var ImgLink=React.createClass({
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
	    var url=this.state.data.map(function(d){
	      return(
	        d.url
	      );
	    });
	    return(
	    <a href={url}>
	      <img alt="button" src="image/Sign-In-Small---Default.png"/>
	    </a>
	    );
	  }

	});
	ReactDOM.render(
	  <ImgLink url="AuthControl" />,
	  document.getElementById('button')
	);