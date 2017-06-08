import React from 'react'
import cssModules from 'react-css-modules'
import { Row, Col, FormControl, FormGroup, ControlLabel, InputGroup } from 'react-bootstrap'
import { MuiThemeProvider, RaisedButton, Divider, Subheader, Dialog } from 'material-ui'
import { connect } from 'react-redux'
import style from './checkout.style.scss'
import { TextFieldGroup } from '../../components/common'
import { userVerificationRequest } from './checkout.action'

@cssModules(style, { errorWhenNotFound: false })
class VerificationFormComponent extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      code: '',
      receivedCode: '',
      phoneNumber: '',
      isSubmit: false,
      isFinish: false,
      error: {}
    }
  }

  onSubmitCode() {
    this.props.userVerificationRequest(this.state.phoneNumber)
      .then(
        res => {
          this.setState({ receivedCode: res, isSubmit: true })
        }
      )

  }

  onVerifyCode(e) {
    e.preventDefault()
    let { code, receivedCode } = this.state

    if (receivedCode === code) {
      this.setState({ isFinish: true })
      this.props.handlerFinishCheckout()
    } else {
      this.setState({ error: {
        failed: '*Input Code is incorrect!'
      }})
    }
  }

  onChangeNumber(e) {
    e.preventDefault()
    this.setState({ [e.target.name]: e.target.value })
  }

  render() {
    let { code, isSubmit, phoneNumber, error} = this.state
    const testCodeForm = (
      <section styleName="signin-section" style={{margin: "10px 0 0 5%", padding: "10px 30px"}}>
        <MuiThemeProvider>
          <Subheader style={{color: "blue", fontSize: "20px"}}>Testing your verification code.</Subheader>
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Divider/>
        </MuiThemeProvider>
        <section style={{marginTop: "20px"}}>
          <FormGroup controlId="formValidationError4">
            <ControlLabel style={{fontWeight: 'normal'}}>
              Input your received code. If you do not receive any message,
              <a href="#">try to send again</a>
              { error.failed && <p style={{color: 'red', margin: 'auto', fontWeight: 'bolder'}}>{error.failed}</p>}
            </ControlLabel>
            {' '}
            <InputGroup>
              <InputGroup.Addon>#</InputGroup.Addon>
              <TextFieldGroup name='code'
                              value={code}
                              type="text"
                              onChange={(e) => this.onChangeNumber(e)}
                              placeholder='Enter your code'
              />
            </InputGroup>
            <FormControl.Feedback />
          </FormGroup>
          <MuiThemeProvider>
            <RaisedButton label="Checking"
                          labelPosition="before"
                          backgroundColor="#f22f46"
                          labelColor="white"
                          onTouchTap={(e) => this.onVerifyCode(e)}
            />
          </MuiThemeProvider>
        </section>
      </section>
    )

    return (
      <Row>
        <Col md={4} mdOffset={4}>
          <section styleName='signin-section' style={{marginLeft: "5%", padding: "10px 30px"}}>
            <MuiThemeProvider>
              <Subheader style={{color: "blue", fontSize: "20px"}}>We need to verify you're a human.</Subheader>
            </MuiThemeProvider>
            <MuiThemeProvider>
              <Divider/>
            </MuiThemeProvider>
            <section style={{marginTop: "20px"}}>
              <FormGroup controlId="formValidationError4">
                <ControlLabel>Your phone's number: </ControlLabel>{' '}
                <InputGroup>
                  <InputGroup.Addon>+84</InputGroup.Addon>
                  <TextFieldGroup name='phoneNumber'
                                  value={phoneNumber}
                                  type="text"
                                  onChange={(e) => this.onChangeNumber(e)}
                                  placeholder='Enter your phone number'
                  />
                </InputGroup>
                <FormControl.Feedback />
              </FormGroup>
              <MuiThemeProvider>
                <RaisedButton label="Verify via SMS"
                              labelPosition="before"
                              backgroundColor="#f22f46"
                              labelColor="white"
                              onTouchTap={() => this.onSubmitCode()}
                />
              </MuiThemeProvider>
            </section>
          </section>
          { isSubmit && testCodeForm }
        </Col>
        <MuiThemeProvider>
          <Dialog
            open={this.state.isFinish}
            title="Congratulations"
            actions={
              <RaisedButton
                label="BACK"
                keyboardFocused={true}
                onTouchTap={() => window.location.replace("/")}
                secondary={true}
              />
            }
          >
            You have been finished to checkout. Click the button to continue shopping or will
            be automatically go back to homepage in 5s.
          </Dialog>
        </MuiThemeProvider>
      </Row>
    )
  }
}

VerificationFormComponent.propTypes = {
  handlerFinishCheckout: React.PropTypes.func.isRequired,
  userVerificationRequest: React.PropTypes.func.isRequired
}

export const VerificationForm = connect(null, { userVerificationRequest })(VerificationFormComponent)