import React from 'react';
import Menu from './component/Menu';
import './style/style.css';
import PropTypes from 'prop-types';
import _ from 'lodash';

class EmployeeInfo extends React.Component {

  static get propTypes() {
    return {
      message: PropTypes.string, // just an example
    }
  }

  constructor(props) {
    super(props);
    // this.getData = this.getData.bind(this);
    this.state = {
      error: null,
      isLoaded: false,
      items: [],
      empInfo: {
        // index: null,
        // name: null,
        // dateOfBirth:null,
        // address: null,
      },
      typeUpdate: null,
      index: null,
    };
  }

  componentDidMount() {
    this.getData();
  }

  onGetDataUpdate(index) {
    this.setState({
      typeUpdate: 'get-update',
      index: index
    }, () => {
      console.log(this.state.typeUpdate + this.state.index)
      this.getData();
    });

  }

  refreshPage() {
    window.location.reload(false);
  }

  onPostDelete(index) {
    this.setState({
      typeUpdate: 'delete',
      index: index
    }, () => {
      this.postData();
    });
    this.refreshPage()
  }

  onPostUpdateData() {
    this.setState({
      typeUpdate: 'update',
    }, () => {
      this.postData();
    });
  }

  getData() {
    fetch("http://localhost:8080/info?"
      + new URLSearchParams({
        typeUpdate: this.state.typeUpdate,
        id: this.state.index
      }),
      {
        method: 'GET',
        // params: { 
        //   typeUpdate: this.state.typeUpdate,
        //   id: this.state.empInfo.index }
        // }
      }
    )
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            items: result.listEmployeeInfo,
            empInfo: !_.isEmpty(result.employeeInfo) ? result.employeeInfo : {},
            typeUpdate: null,
          });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  postData() {
    fetch('http://localhost:8080/info', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      // refer to https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/stringify
      // to get more infor about create json with stringify
      body: JSON.stringify({
        typeUpdate: this.state.typeUpdate,
        index: this.state.index,
        employee: {
          index: this.state.empInfo.index,
          name: this.state.empInfo.name,
          dateOfBirth: this.state.empInfo.dateOfBirth,
          address: this.state.empInfo.address,
          idDep: !_.isEmpty(this.state.empInfo.idDep)?this.state.empInfo.idDep:1,
        }
      })
    })
  }

  handleChange(event, typeName) {
    switch (typeName) {
      /*    onChange={(e) => this.setState(prevState => {
       let empInfo = Object.assign({}, prevState.empInfo);  // creating copy of state variable jasper
       empInfo.address = e.target.value;                     // update the name property, assign a new value                 
        return { empInfo };                                 // return new object jasper object
     })}  */
      case 'index':
        this.setState(prevState => ({
          empInfo: {                   // object that we want to update
            ...prevState.empInfo,    // keep all other key-value pairs
            index: event.target.value       // update the value of specific key
          }
        }))
        break;
      case 'name':
        this.setState(prevState => ({
          empInfo: {
            ...prevState.empInfo,
            name: event.target.value
          }
        }))
        break;
      case 'dateOfBirth':
        this.setState(prevState => ({
          empInfo: {
            ...prevState.empInfo,
            dateOfBirth: event.target.value
          }
        }))
        break;
      case 'address':
        this.setState(prevState => ({
          empInfo: {
            ...prevState.empInfo,
            address: event.target.value
          }
        }))
        break;
      case 'idDep':
        this.setState(prevState => ({
          empInfo: {
            ...prevState.empInfo,
            idDep: event.target.value
          }
        }))
        break;

      default:
        break;

    }
    /* this.setState(prevState => ({
      empInfo: {                   // object that we want to update
          ...prevState.empInfo,    // keep all other key-value pairs
          address: event.target.value       // update the value of specific key
      }

    })) */
  }

  render() {
    const { error, isLoaded, items } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      /*  if ( typeof(items) !== "undefined" && items !== null ) {
       }
  */
      const listEmps = !_.isEmpty(items) ? items.map((item) =>
        <tr key={item.index}>

          <td> {item.index}</td>
          <td> {item.name}</td>
          <td> {item.dateOfBirth}</td>
          <td> {item.address}</td>
          <td> {item.idDep}</td>
          <td>
            <input type="submit" value="Edit" onClick={() => this.onGetDataUpdate(item.index)} />
            <input type="submit" value="Delete" onClick={() => this.onPostDelete(item.index)} />
          </td>
        </tr>
      ) : null;
      return (

        <div>
          <Menu></Menu>

          <div className="frame">
            <form >
              {/* <input type="hidden" name="type-update" value="update" /> */}

              <table className="editInfo">
                <tr>
                  <td>
                    <div>
                      <div>Id:</div>
                      <div>
                        <input type="text" name="index" value={this.state.empInfo.index}
                          onChange={(e) => {
                            this.handleChange(e, "index")
                          }} />
                      </div>
                    </div>
                  </td>
                  <td>
                    <div>

                      <div>Name:</div>
                      <div>
                        <input type="text" name="name" value={this.state.empInfo.name}
                          onChange={(e) => {
                            this.handleChange(e, "name")
                          }} />
                      </div>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>

                    <div>

                      <div>date_of_birth:</div>
                      <div>
                        <input type="date" name="date_of_birth"
                          value={this.state.empInfo.dateOfBirth}
                          onChange={(e) => {
                            this.handleChange(e, "dateOfBirth")
                          }} />
                      </div>
                    </div>
                  </td>
                  <td>
                    <div>
                      <div>Address:</div>
                      <div>
                        <input type="text" name="address" value={this.state.empInfo.address}

                          onChange={(e) => {
                            this.handleChange(e, "address")
                          }
                          }

                        />
                      </div>

                    </div>
                    <div>
                      <div>Id Dep:</div>
                      <div>
                        <select name="idDep"
                          value={this.state.selectValue}
                          //  onChange={(e) => this.setState({idDep: e.target.value})} 
                          onChange={(e) => {
                            this.handleChange(e, "idDep")
                          }
                          }
                        >
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                          <option value="6">6</option>
                        </select>
                      </div>
                    </div>
                  </td>
                </tr>
              </table>
              <div>
                <br />
                <div>
                  <input type="submit" value="Save" onClick={() => this.onPostUpdateData()} />
                </div>
              </div>
            </form>
          </div>

          <table className="tableInfo">
            <tr className="row-title">
              <td>ID</td>
              <td>Name</td>
              <td>Date Of Birth</td>
              <td>Address</td>
              <td>Id Dep</td>
              <td>Edit</td>
            </tr>
            {listEmps}
          </table>
        </div>
      );
    }
  }
}

export default EmployeeInfo;