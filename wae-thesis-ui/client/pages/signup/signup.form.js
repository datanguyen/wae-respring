import React from 'react'
import cssModules from 'react-css-modules'
import { MuiThemeProvider, TextField, RaisedButton } from 'material-ui'
import { browserHistory } from 'react-router'
import isEmpty from 'lodash/isEmpty'
import style from './signup.style.scss'


@cssModules(style, {errorWhenNotFound: false})
export class SignUpForm extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      fullname: '',
      username: '',
      password: '',
      confirmPassword: '',
      errors: {},
      isLoading: false
    }
  }

  validateInput() {
    let errors = {}
    let { fullname, username, password, confirmPassword} = this.state

    if (fullname === '') {
      errors.fullname = "This is a required field"
    }

    if (username === '') {
      errors.username = "This is a required field"
    }

    if (password === '') {
      errors.password = "This is a required field"
    }

    if (confirmPassword === '') {
      errors.confirmPassword = "This is a required field"
    }

    if (password !== confirmPassword) {
      errors.confirmPassword = "Password does not matched"
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
    e.preventDefault()
    this.setState({ [e.target.name]: e.target.value })
  }

  onSubmit(e) {
    e.preventDefault()
    let { username, password, fullname } = this.state
    let { isValid } = this.validateInput()

    if (!isValid) {
      this.setState({ errors: {}})
      this.props.userSignUpRequest({username, password, fullname})
        .then(
          res => browserHistory.push('/signin')
        )
        .catch(err => console.log(err))
    }
  }


  render() {

    let { fullname, username, password, confirmPassword, errors, isLoading } = this.state
    let inputStyle = { width: "300px", margin: "auto", display: "block" }

    return (
      <form styleName="signin-section">
        <h1 styleName="signin-section__header">
          <span style={{color: 'red'}}>W</span>A<span style={{color: 'blue'}}>D</span>E
        </h1>
        <h1 styleName="signin-section__header" style={{fontWeight: "normal"}}>
          Create account
        </h1>
        { errors.form && <p styleName="signin-section--failed">{errors.form}</p> }
        <MuiThemeProvider>
          <TextField name="fullname"
                     value={fullname}
                     errorText={errors.fullname}
                     floatingLabelText="Fullname"
                     style={inputStyle}
                     floatingLabelStyle={{color: "#252f3e"}}
                     onChange={(e) => this.onChange(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <TextField name="username"
                     value={username}
                     errorText={errors.username}
                     floatingLabelText="Email"
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
          <TextField name="confirmPassword"
                     value={confirmPassword}
                     errorText={errors.confirmPassword}
                     floatingLabelText="Confirm Password"
                     type="password"
                     style={inputStyle}
                     floatingLabelStyle={{color: "#252f3e"}}
                     onChange={(e) => this.onChange(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <RaisedButton label="Sign Up"
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

SignUpForm.propTypes = {
  userSignUpRequest: React.PropTypes.func.isRequired
}