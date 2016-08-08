
//////////font-color
var styleFontColor = {
    color: "gray"
};

////Firstname
var FirstName=React.createClass({
	  loadInfoFromServer:function(){
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
		  this.loadInfoFromServer();
	  },
	  render: function(){
		  console.log(this.state.data);
	    var firstname=this.state.data.map(function(d){
	      return(
	        d.firstName
	      );
	    });
	    return(
	    <div>
	      <h2>{firstname}</h2>
	    </div>
	    );
	  }

	});
	ReactDOM.render(
	  <FirstName url="GetFirName" />,
	  document.getElementById('firstname')
	);
//////////lastname
	var LastName=React.createClass({
		  loadInfoFromServer:function(){
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
			  this.loadInfoFromServer();
		  },
		  render: function(){
			  console.log(this.state.data);
		    var lastname=this.state.data.map(function(d){
		      return(
		        d.lastName
		      );
		    });
		    return(
		      <h2>{lastname}</h2>
		    );
		  }

		});
		ReactDOM.render(
		  <LastName url="GetLastName" />,
		  document.getElementById('lastname')
		);

/////////Headline
var Headline=React.createClass({
    loadInfoFromServer:function(){
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
      this.loadInfoFromServer();
    },
    render: function(){
      console.log(this.state.data);
      var headline=this.state.data.map(function(d){
        return(
          d.headline
        );
      });
      return(
      <div>
        <h4 style={styleFontColor}>{headline}</h4>
      </div>
      );
    }

  });
ReactDOM.render(
  <Headline url="GetHead" />,
  document.getElementById('headline')
);
/////////location
var Location=React.createClass({
    loadInfoFromServer:function(){
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
      this.loadInfoFromServer();
    },
    render: function(){
      console.log(this.state.data);
      var location=this.state.data.map(function(d){
        return(
          d.location
        );
      });
      return(
      <div>
        <h4 style={styleFontColor}>{location}</h4>
      </div>
      );
    }

  });
ReactDOM.render(
  <Location url="GetLoc" />,
  document.getElementById('location')
);
///////industry
var Industry=React.createClass({
    loadInfoFromServer:function(){
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
      this.loadInfoFromServer();
    },
    render: function(){
      console.log(this.state.data);
      var industry=this.state.data.map(function(d){
        return(
          d.industry
        );
      });
      return(
      <div>
        <h4 style={styleFontColor}>{industry}</h4>
      </div>
      );
    }

  });
ReactDOM.render(
  <Industry url="GetInd" />,
  document.getElementById('industry')
);
////////////summary

var Summary=React.createClass({
			  loadInfoFromServer:function(){
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
				  this.loadInfoFromServer();
			  },
			  render: function(){
				  console.log(this.state.data);
			    var summary=this.state.data.map(function(d){
			      return(
			        d.summary
			      );
			    });
			    return(
			    <div>
			      <h2>Summary</h2>
			      <p>{summary}</p>
			    </div>
			    );
			  }

			});
			ReactDOM.render(
			  <Summary url="GetSum" />,
			  document.getElementById('summary')
			);
///////////full profile button
var Button=React.createClass({
    loadInfoFromServer:function(){
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
      this.loadInfoFromServer();
    },
    render: function(){
      console.log(this.state.data);
      var url=this.state.data.map(function(d){
        return(
          d.siteStandardProfileRequest
        );
      });
      return(
        <a href={url}>View full profile</a>

      );
    }

  });
ReactDOM.render(
  <Button url="GetFullView" />,
  document.getElementById('fullfile')
);
