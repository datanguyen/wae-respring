import React from 'react'
import cssModules from 'react-css-modules'
import { MuiThemeProvider, TextField, RaisedButton } from 'material-ui'
import { browserHistory } from 'react-router'
import isEmpty from 'lodash/isEmpty'
import style from './signin.style.scss'


@cssModules(style, {errorWhenNotFound: false})
export class SignInForm extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      errors: {},
      isLoading: false
    }
  }

  validateInput() {
    let errors = {}

    if (this.state.username === '') {
      errors.username = "This is a required field"
    }

    if (this.state.password === '') {
      errors.password = "This is a required field"
    }

    if (!isEmpty(errors)) {
      this.setState({ errors })
    }

    return {
      errors,
      isValid: !isEmpty(errors)
    }
  }

  onChange(e) {
    e.preventDefault();
    this.setState({ [e.target.name]: e.target.value })
  }

  onSubmit(e) {
    e.preventDefault()
    let {isValid} = this.validateInput()
    if (!isValid) {

      if (this.state.username === "ntdat") {
        this.setState({errors: {}, isLoading: true})
        browserHistory.push("/")
      } else {
        this.setState({errors: {}})
        this.setState({ errors: { form: '*Authentication Failed'}})
      }
    }
  }


  render() {

    let { username, password, errors, isLoading } = this.state
    let inputStyle ={ width: "300px", margin: "auto", display: "block" }

    return (
      <form styleName="signin-section" className="animated rollIn">
        <h1 styleName="signin-section__header">
          <span style={{color: 'red'}}>W</span>A<span style={{color: 'blue'}}>D</span>E
        </h1>
        <h1 styleName="signin-section__header" style={{fontWeight: "normal"}}>
          Sign in for shopping
        </h1>
        { errors.form && <p styleName="signin-section--failed">{errors.form}</p> }
        <MuiThemeProvider>
          <TextField name="username"
                     value={username}
                     errorText={errors.username}
                     floatingLabelText="Username"
                     style={inputStyle}
                     floatingLabelStyle={{color: "#252f3e"}}
                     onChange={(e) => this.onChange(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <TextField name="password"
                     value={password}
                     type="password"
                     errorText={errors.password}
                     floatingLabelText="Password"
                     style={inputStyle}
                     floatingLabelStyle={{color: "#252f3e"}}
                     onChange={(e) => this.onChange(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <RaisedButton label="Sign In"
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="white"
                        fullWidth={true}
                        style={{margin: "40px 0 10px 0"}}
                        onTouchTap={(e) => this.onSubmit(e)}
                        disabled={isLoading}
          />
        </MuiThemeProvider>
      </form>
    )
  }
}